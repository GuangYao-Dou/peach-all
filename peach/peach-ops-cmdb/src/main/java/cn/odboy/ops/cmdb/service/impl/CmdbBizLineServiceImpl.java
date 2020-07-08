package cn.odboy.ops.cmdb.service.impl;

import cn.odboy.ops.cmdb.dao.ICmdbBizLineDao;
import cn.odboy.ops.cmdb.model.CmdbBizLine;
import cn.odboy.ops.cmdb.service.ICmdbBizLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CmdbBizLineServiceImpl extends ServiceImpl<ICmdbBizLineDao, CmdbBizLine> implements ICmdbBizLineService {
}
