/**
 *    Auth:riozenc
 *    Date:2018年9月25日 上午11:10:30
 *    Title:wms.webapp.wrk.action.ChartAction.java
 **/
package wms.webapp.wrk.action;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riozenc.quicktool.common.util.date.NDateUtil;
import com.riozenc.quicktool.common.util.json.JSONUtil;
import com.riozenc.quicktool.springmvc.webapp.action.BaseAction;

import wms.webapp.sys.domain.UserDomain;
import wms.webapp.sys.service.IUserService;
import wms.webapp.wrk.domain.TaskDomain;
import wms.webapp.wrk.service.ITaskService;

@ControllerAdvice(assignableTypes = ChartAction.class)
@RequestMapping("chart")
public class ChartAction extends BaseAction {

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Autowired
	@Qualifier("taskServiceImpl")
	private ITaskService taskService;

	@Override
	public String getIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(params = "method=userTaskChartIndex")
	public String userTaskChartIndex() {
		return "chart/userTaskChart.jsp";
	}

	@RequestMapping(params = "method=getUserTaskChart")
	public void getUserTaskChart() {

	}

	@ResponseBody
	@RequestMapping(params = "method=getJson")
	public String getJson(UserDomain userDomain, Date startDate, Date endDate) {

//		List<TaskDomain> list = taskService.getTasksByUser(userDomain);
		Map<String, Object> params = new HashMap<>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<TaskDomain> list = taskService.getTasksByMap(params);

		List<String> yAxisData = new LinkedList<>();
		List<Date> startDateList = new LinkedList<>();
		List<Integer> planStartData = new LinkedList<>();
		List<Integer> actualStartData = new LinkedList<>();// 延期时间
		List<Long> blankData = new LinkedList<>();// 空白时间
		List<String> userList = new LinkedList<>();

		Set<Date> dateSet = new HashSet<>();
		Set<Integer> daySet = new HashSet<>();

		for (TaskDomain task : list) {
			if (task.getStartDate() != null) {
				dateSet.add(task.getStartDate());
				startDateList.add(task.getStartDate());
			}
			if (task.getEndDate() != null) {
				dateSet.add(task.getEndDate());
			}
			if (task.getPlanDays() != null) {
				daySet.add(task.getPlanDays());
			}
			if (task.getActualDays() != null) {
				daySet.add(task.getActualDays());
			}
			if (task.getUserName() != null) {
				userList.add(task.getUserName());
			}
			yAxisData.add(task.getTaskName());
			planStartData.add(task.getPlanDays());
			actualStartData.add(task.getActualDays());
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("title", "标题");
		// x轴范围 最早的启动时间 -- 最晚的结束时间
		// y轴 显示内容
		resultMap.put("yAxisData", yAxisData);
		resultMap.put("planStartData", planStartData);
		resultMap.put("actualStartData", actualStartData);
		resultMap.put("blankData", blankData);
		resultMap.put("users", userList);

		if (list.size() != 0) {
			Date minDate = Collections.min(dateSet);
			Date maxDate = Collections.max(dateSet);
			Integer dayPostpone = Collections.max(daySet);

			// 计算空白时间:任务起始时间-最小任务起始时间
			for (Date date : startDateList) {
				blankData.add(NDateUtil.between(minDate, date));
			}
			resultMap.put("startDate", minDate);
			resultMap.put("endDate", NDateUtil.Date(maxDate, dayPostpone));
		}

		return JSONUtil.toJsonString(resultMap);
	}

}
