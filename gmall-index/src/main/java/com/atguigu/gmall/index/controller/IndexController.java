package com.atguigu.gmall.index.controller;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.index.service.IndexService;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    IndexService indexService;
    @GetMapping({"index.html","/"})
    public String toIndex(Model model, HttpServletRequest request){
        System.out.println(request.getHeader("userId")+"===================");


        //三级分类数据
        List<CategoryEntity> categories = this.indexService.queryLv1CategoriesByPid();
        model.addAttribute("categories",categories);


        return "index";
    }
    @GetMapping("index/cates/{pid}")
    @ResponseBody
    public ResponseVo<List<CategoryEntity>>queryLv12CategoriesWithSubByPid(@PathVariable("pid")Long pid){
       List<CategoryEntity>categoryEntities =  this.indexService.queryLv12CategoriesWithSubByPid(pid);
       return ResponseVo.ok(categoryEntities);
    }
    @GetMapping("index/test/lock")
    @ResponseBody
    public ResponseVo testLock(){
        this.indexService.testLock();
        return ResponseVo.ok();
    }
    @GetMapping("index/test/read")
    @ResponseBody
    public ResponseVo testRead(){
        this.indexService.testRead();
            return ResponseVo.ok("读取成功");


    }
    @GetMapping("index/test/write")
    @ResponseBody
    public ResponseVo testWrite(){
        this.indexService.testWrite();
            return ResponseVo.ok("读取成功");


    }
    @GetMapping("index/test/latch")
    @ResponseBody
    public ResponseVo testLatch(){
        this.indexService.testLatch();
        return ResponseVo.ok("班长锁门了");


    }
    @GetMapping("index/test/countdown")
    @ResponseBody
    public ResponseVo testcountdown(){
        this.indexService.testCountDown();
        return ResponseVo.ok("同学出来了");


    }

}
