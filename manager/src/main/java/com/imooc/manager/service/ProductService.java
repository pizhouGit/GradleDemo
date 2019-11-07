package com.imooc.manager.service;

import com.imooc.entity.Product;
import com.imooc.entity.enums.ProductStatus;
import com.imooc.manager.dao.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品服务类
 */
@Service
public class ProductService {
    //定义日记
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * 添加产品
     */
    public Product addProduct(Product product) {
        logger.debug("创建产品，参数：{}", product);
        //数据校验
        checkProduct(product);
        //设置默认值
        setDefault(product);
        Product result = productRepository.save(product);
        logger.debug("创建产品，结果{}", result);
        return result;
    }


    /**
     * 查询单个产品
     */
    public Product findOne(String id) {
        logger.debug("查询单个产品,结果={}", id);
        Assert.notNull(id, "产品编号不为空");

        Product one = productRepository.findOne(id);
        logger.debug("查询单个产品，结果={}", one);
        return one;
    }

    /**
     *
     */

    /**
     * 产品数据校验
     * 1.非空数据
     * 2.收益率要0-30以内
     * 3.投资步长需为整数
     *
     * @param product
     */
    private void checkProduct(Product product) {

        Assert.notNull(product.getId(), "产品编号不为空");

        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, "收益率范围错误");

        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "投资步长需为整数");


    }


    /**
     * 设置默认值
     * 1.创建时间
     * 2.更新时间
     * 3.投资步长
     * 4.锁定期
     *
     * @param product
     */
    public void setDefault(Product product) {

        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }

        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }

        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }

        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }

        if (product.getStatus() == null) {
            //审核中
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }
}
