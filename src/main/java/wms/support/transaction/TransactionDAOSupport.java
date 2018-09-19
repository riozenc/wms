/**
 *    Auth:riozenc
 *    Date:2018年5月10日 上午10:29:32
 *    Title:cis.web.support.transaction.TransactionDAOSupport.java
 **/
package wms.support.transaction;

import org.springframework.context.annotation.Configuration;

import com.riozenc.quicktool.springmvc.transaction.registry.TransactionDAORegistryPostProcessor;

@Configuration
public class TransactionDAOSupport extends TransactionDAORegistryPostProcessor {

}
