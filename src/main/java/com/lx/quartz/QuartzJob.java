package com.lx.quartz;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lx.util.DateUtils;

/**
 * 定时任务,分发器
 * @author lx
 *
 */
public class QuartzJob {
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * 控制不能同步操作数据
	 */
	private static AtomicBoolean oneWork=new AtomicBoolean(false);
	private static AtomicBoolean twoWork=new AtomicBoolean(false);

	/**
	 * 定时任务一
	 */
	public void work(){
		if (QuartzJob.oneWork.compareAndSet(false, true)) {
			try {
				System.out.println("定时任务11----------------------"
						+ DateUtils.format(DateUtils.SECOND_FORMAT, new Timestamp(System.currentTimeMillis())));
			} catch (Exception e) {
				log.error("定时任务1------" + e, e);
			} finally {
				QuartzJob.oneWork.compareAndSet(true, false);
			}
		}
	}
	
	/**
	 * 定时任务一
	 */
	public void workTwo(){
		if (QuartzJob.twoWork.compareAndSet(false, true)) {
			try {
				System.out.println("定时任务22-------------------"+DateUtils.format(DateUtils.SECOND_FORMAT , new Timestamp(System.currentTimeMillis())));
			} catch (Exception e) {
				log.error("定时任务2------" + e, e);
			} finally {
				QuartzJob.twoWork.compareAndSet(true, false);
			}
		}
		  
	}
}
