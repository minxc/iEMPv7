package org.minxc.emp.system.scheduler;

import org.minxc.emp.system.core.model.SysScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * quartz不允许并发执行
 *
 * @author didi
 */
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SysScheduleJob sysScheduleJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysScheduleJob);
    }
}
