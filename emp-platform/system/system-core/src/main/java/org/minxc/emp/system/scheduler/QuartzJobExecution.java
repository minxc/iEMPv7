package org.minxc.emp.system.scheduler;

import org.minxc.emp.system.core.model.SysScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * quartz任务执行
 *
 * @author didi
 */
public class QuartzJobExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SysScheduleJob sysScheduleJob)throws Exception {
        JobInvokeUtil.invokeMethod(sysScheduleJob);
    }
}
