Ext.define('js.custom.basicDataLayout', {
	extend : 'Ext.panel.Panel',

	id : 'panel',
	alias : 'widget.basicDataLayout',
	layout : {
		type : 'vbox',
		align : 'stretch',
		padding : 5,
	},

	constructor : function(config) {

		var _gridStore = config._gridStore;
		var _formItems = config._formItems;

		var _gridColumns = config._gridColumns;

		var selectButton = new Ext.button.Button({
			text : '查询',
			id : 'selectButton',
			handler :  function(me) {
				if (me.up("panel").up("panel")._form.isValid()) {
					me.up("panel").getStore().currentPage = 1;
					me.up("panel").getStore().reload();
				} else {
					alert("查询条件有误,请仔细检查");
				}
			}
		});

		var _gridTbar = config._gridTbar || new Array();

		var createGridButton = function() {
			_gridTbar.push('-');
			_gridTbar.push(selectButton);
			return _gridTbar;
		};

		var _ischeckBox = config._ischeckBox || false;
		var _selModel = config._selModel;

		var createSelModel = function() {
			if (_ischeckBox) {
				_selModel = Ext.create('Ext.selection.CheckboxModel', {
							mode : "SIMPLE"
						});
			}
			return _selModel;
		};

		var createGridPanel = function() {

			return new Ext.grid.Panel({
				id : 'gridPanel',
				flex : 2,
				store : _gridStore,
				columns : _gridColumns,
				selModel : createSelModel(),
				tbar : createGridButton(),

				forceFit: true,

				// store: Ext.data.StoreManager.lookup('simpsonsStore'),

				bbar : [{
					xtype : 'pagingtoolbar',
					store : _gridStore,
					dock : 'bottom',
					displayInfo : true,
					doRefresh : function() {

						var me = this,
						// 当前分页器页数
						current = me.store.currentPage;

						if (me.fireEvent('beforechange', me, current) !== false) {
							// 数据不足时出现bug
							// me.store.loadPage(current);
							me.store.loadPage(1);
						}
					}
				}]
			});
		};

		this._grid = config._gridPanel || createGridPanel();

		this._form = new Ext.form.Panel({
					id : 'formPanel',
					layout : {
						type : 'hbox',
						pack : 'center',
						align : 'middle'
					},
					
					defaults: { // defaults are applied to items, not the container
						labelAlign : 'right',
						padding:'0 0 0 10'
					},

					items : _formItems,
					flex : 1
				});

		config.items = [this._form, {
					xtype : 'splitter'
				}, this._grid],

		this.callParent(arguments); // calls Ext.panel.Panel's constructor
		// ...
	},

	init : function() {
		this.randomId = Ext.id();
		this.baseViewportId = arguments[0].baseViewportId
				|| (this.randomId + '-baseViewportId');
		var viewport = Ext.create('Ext.container.Viewport', {
			id : this.baseViewportId,
			layout : 'fit',
			items : [this]
		});
	}

});

// 刷新方法
var reload = function() {
	Ext.getCmp("gridPanel").getStore().load();
};
// 导出excel按钮
var creatDeriveButton = function(url) {
	return new Ext.button.Button({
				text : '导出Excel',
				handler : function() {

					Ext.MessageBox.confirm('提示', '确定要导出相关的Excel吗?', function(
									btn) {
								if (btn == 'yes') {
									var formValues = Ext.getCmp("formPanel")
											.getValues();

									var f = document.createElement("form");
									document.body.appendChild(f);

									// 循环插入数据
									for (x in formValues) {

										if (x) {
											var i = document
													.createElement("input");
											i.type = "hidden";
											f.appendChild(i);
											i.name = x;
											i.value = formValues[x];
										}
									}
									f.action = url;
									f.submit();
								}
							});
				}
			});
};
