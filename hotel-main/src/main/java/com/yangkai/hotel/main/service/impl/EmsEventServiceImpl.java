package com.yangkai.hotel.main.service.impl;

import com.github.pagehelper.PageHelper;
import com.yangkai.hotel.main.bo.AdminUserDetails;
import com.yangkai.hotel.main.dao.EmsEventDao;
import com.yangkai.hotel.main.service.EmsEventService;
import com.yangkai.hotel.mbg.mapper.EmsEventMapper;
import com.yangkai.hotel.mbg.model.EmsEvent;
import com.yangkai.hotel.mbg.model.EmsEventExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (EmsEvent)表服务实现类
 *
 * @author makejava
 * @since 2020-11-02 18:45:19
 */
@Service("emsEventService")
public class EmsEventServiceImpl implements EmsEventService {
    @Autowired
    private EmsEventDao emsEventDao;
    @Autowired
    private EmsEventMapper emsEventMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EmsEvent queryById(Long id) {
        return this.emsEventDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param emsEvent 实例对象
     * @return 实例对象
     */
    @Override
    public EmsEvent insert(EmsEvent emsEvent) {
        this.emsEventDao.insert(emsEvent);
        return emsEvent;
    }



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.emsEventDao.deleteById(id) > 0;
    }
    @Override
    public  List<EmsEvent> fetchList(EmsEvent emsEvent, Integer pageSize, Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        EmsEventExample example=new EmsEventExample();
        EmsEventExample.Criteria criteria = example.createCriteria();
        if (emsEvent.getType()!=null){
            criteria.andTypeEqualTo(emsEvent.getType());
        }
        if (emsEvent.getDescription()!=null&&emsEvent.getDescription().trim().length()>0){
            criteria.andDescriptionLike("%"+emsEvent.getDescription()+"%");
        }
        if (emsEvent.getSubject()!=null&&emsEvent.getSubject().trim().length()>0){
            criteria.andSubjectLike("%"+emsEvent.getSubject()+"%");
        }
        example.setOrderByClause("create_time DESC,finish_time DESC,receive_time DESC");
        return emsEventMapper.selectByExample(example);
    }

    @Override
    public int report(EmsEvent event) {
        event.setCreateTime(new Date());
        if (event.getId()==null){
            return emsEventDao.insert(event);
        }else {
            return emsEventMapper.updateByPrimaryKeySelective(event);
        }
    }
    @Override
    public synchronized int deleteEventById(Long id){
        EmsEvent emsEvent = emsEventMapper.selectByPrimaryKey(id);
        if (emsEvent==null){
            //事件不存在
            return 2;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        if (!username.equals(emsEvent.getReportPeople())){
            //操作者不是事件创建者
            return 3;
        }
        if (emsEvent.getCheckStatus()!=null&&emsEvent.getCheckStatus()!=0){
            //事件已提交,无法删除
            return 4;
        }
        int count = emsEventMapper.deleteByPrimaryKey(id);
        if (count!=1){
            return 5;
        }else {
            return 1;
        }
    }

    @Override
    public EmsEvent getEventById(Long id) {
        return emsEventMapper.selectByPrimaryKey(id);
    }

    @Override
    public synchronized int cancelReportById(Long id) {
        EmsEvent emsEvent = emsEventMapper.selectByPrimaryKey(id);
        if (emsEvent==null){
            //事件不存在
            return 2;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        if (!username.equals(emsEvent.getReportPeople())){
            //操作者不是事件创建者
            return 3;
        }
        if (emsEvent.getCheckStatus()!=null&&emsEvent.getCheckStatus()==3){
            //事件已经审核通过,无法删除
            return 4;
        }
        emsEvent.setCheckStatus(0);
        int count = emsEventMapper.updateByPrimaryKeySelective(emsEvent);
        if (count!=1){
            return 5;
        }else {
            return 1;
        }
    }

    @Override
    public int reportFromDraft(Long id) {
        EmsEvent emsEvent=emsEventMapper.selectByPrimaryKey(id);
        if (emsEvent==null){
            return 2;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        if (!username.equals(emsEvent.getReportPeople())){
            //操作者不是事件创建者
            return 3;
        }
        if (emsEvent.getCheckStatus()!=null&&emsEvent.getCheckStatus()!=0&&emsEvent.getCheckStatus()!=2){
            //事件已提交
            return 4;
        }
        emsEvent.setStatus(0);
        emsEvent.setCheckStatus(1);
        int count=emsEventMapper.updateByPrimaryKeySelective(emsEvent);
        if (count!=1){
            return 5;
        }else {
            return 1;
        }
    }

    @Override
    public int check(EmsEvent params) {
        EmsEvent emsEvent=emsEventMapper.selectByPrimaryKey(params.getId());
        emsEvent.setCheckStatus(params.getCheckStatus());
        emsEvent.setCheckOpinion(params.getCheckOpinion());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        emsEvent.setCheckPeople(username);
        emsEvent.setCheckTime(new Date());
        return emsEventMapper.updateByPrimaryKeySelective(emsEvent);
    }

    @Override
    public int changeStatus(EmsEvent params) {
        EmsEvent emsEvent=emsEventMapper.selectByPrimaryKey(params.getId());
        emsEvent.setStatus(params.getStatus());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=( (AdminUserDetails)authentication.getPrincipal()).getUsername();
        emsEvent.setHandlePeople(username);
        if (params.getStatus()==1){
            emsEvent.setReceiveTime(new Date());
        }
        if (params.getStatus()==2){
            emsEvent.setFinishTime(new Date());
        }
        emsEvent.setReceiveTime(new Date());
        return emsEventMapper.updateByPrimaryKeySelective(emsEvent);
    }
}