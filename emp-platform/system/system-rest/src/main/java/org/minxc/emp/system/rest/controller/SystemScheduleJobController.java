package org.minxc.emp.system.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.constant.BaseStatusCode;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.system.core.manager.SysScheduleJobLogManager;
import org.minxc.emp.system.core.model.SysScheduleJob;
import org.minxc.emp.system.core.model.SysScheduleJobLog;
import org.minxc.emp.system.scheduler.QuartzManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统任务计划
 *
 * @author didi
 */
@Slf4j
@RestController
@RequestMapping("/sys/scheduleJob")
public class SystemScheduleJobController extends GenericController {


    @Resource
    private QuartzManagerService quartzManagerService;

    @Resource(name = "sysScheduleJobLogManager")
    private SysScheduleJobLogManager sysScheduleJobLogManager;

    /**
     * 查询列表
     */
    @RequestMapping("list")
    public PageJson list(HttpServletRequest request) {
        QueryFilter queryFilter = getQueryFilter(request);
        return new PageJson(quartzManagerService.selectList(queryFilter));
    }

    /**
     * 根据系统执行计划ID得到
     *
     * @param id 执行计划ID
     */
    @RequestMapping("get")
    public ResultMsg<SysScheduleJob> get(@RequestParam("id") String id) {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        return new ResultMsg<>(sysScheduleJob);
    }

    /**
     * 修改系统执行计划
     *
     * @param sysScheduleJob 执行计划
     * @return
     */
    @PostMapping("edit")
    public ResultMsg<String> edit(@RequestBody SysScheduleJob sysScheduleJob) {
        try {
            if (StringUtils.isEmpty(sysScheduleJob.getId())) {
                quartzManagerService.addSysScheduleJob(sysScheduleJob);
            } else {
                quartzManagerService.updateSysScheduleJob(sysScheduleJob);
            }
            ResultMsg<String> resultMsg = new ResultMsg<>(sysScheduleJob.getId());
            resultMsg.setMsg("操作成功");
            return resultMsg;
        } catch (BusinessException e) {

            return new ResultMsg<>(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultMsg<>(BaseStatusCode.SYSTEM_ERROR, BaseStatusCode.SYSTEM_ERROR.getDesc());
        }
    }

    /**
     * 检查是否存在
     *
     * @param name  计划名称
     * @param group 计划分组
     * @return
     */
    @PostMapping("checkExists")
    public boolean checkExists(@RequestParam(name = "name") String name, @RequestParam("group") String group) {

        return quartzManagerService.checkExists(name, group);
    }

    /**
     * 启用任务计划
     *
     * @param id
     * @param enable
     * @return
     * @throws Exception
     */
    @CatchError(write2response = true)
    @RequestMapping("enable")
    public ResultMsg<Void> enable(@RequestParam("id") String id, @RequestParam("enable") boolean enable) throws Exception {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        if (enable) {
            quartzManagerService.enableSysScheduleJob(sysScheduleJob);
        } else {
            quartzManagerService.disableSysScheduleJob(sysScheduleJob);
        }

        return new ResultMsg<>(null);
    }

    /**
     * 立即运行一次
     *
     * @param id 执行计划ID
     * @return
     * @throws Exception
     */
    @RequestMapping("runOnce")
    @CatchError(write2response = true)
    public ResultMsg<?> runOnce(@RequestParam("id") String id) throws Exception {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        quartzManagerService.runOnce(sysScheduleJob);
        return new ResultMsg<>();
    }

    /**
     * 移除
     *
     * @param id 执行计划ID
     * @return
     * @throws Exception
     */
    @RequestMapping("remove")
    @CatchError(write2response = true)
    public ResultMsg<?> remove(@RequestParam("id") String id) throws Exception {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        quartzManagerService.removeSysScheduleJob(sysScheduleJob);
        return new ResultMsg<>();
    }

    /**
     * 执行计划日志列表
     *
     * @param jobId   任务编号
     * @param request
     * @return
     */
    @RequestMapping("log/list")
    @CatchError(write2response = true)
    public PageJson listSysScheduleJobLog(@RequestParam("jobId") String jobId, HttpServletRequest request) {
        QueryFilter queryFilter = getQueryFilter(request);
        queryFilter.addFilter("job_id", jobId, QueryOperation.EQUAL);
        return new PageJson(sysScheduleJobLogManager.query(queryFilter));
    }

    /**
     * 执行计划详细日志
     * @param id
     *          日志ID
     * @return
     */
    @RequestMapping("log/detail")
    @CatchError(write2response = true)
    public ResultMsg<?> getLogDetail(@RequestParam("id")String id){
       SysScheduleJobLog sysScheduleJobLog = sysScheduleJobLogManager.get(id);
       return new ResultMsg<>(sysScheduleJobLog);
    }
}
