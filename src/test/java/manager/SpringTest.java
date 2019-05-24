package manager;

import cn.hutool.core.util.IdUtil;
import com.voucher.manage.dao.CurrentDao;
import com.voucher.manage2.constant.FileConstant;
import com.voucher.manage2.dto.MenuDTO;
import com.voucher.manage2.tkmapper.entity.Menu;
import com.voucher.manage2.tkmapper.mapper.MenuMapper;
import com.voucher.manage2.tkmapper.mapper.TableAliasMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml", "classpath:/spring-mybatis.xml"})
public class SpringTest {
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private CurrentDao currentDao;
    @Autowired
    private TableAliasMapper tableAliasMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void test1() {
        //FileConstant[] values = FileConstant.values();
        //for (FileConstant value : values) {
        MenuDTO menu = new MenuDTO();
        menu.setRootGuid("a46015f715a94b7ea3668e6e89b23b16");
        menu.setGuid(IdUtil.simpleUUID());
        menu.setLevel(2);
        menu.setName("资产图片");
        menu.setParentGuid("e70c684df1474f85b8304f0f4e34024d");
        menuMapper.insert(menu);
        //}
    }
}
