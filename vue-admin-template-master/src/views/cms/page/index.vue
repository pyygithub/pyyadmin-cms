<template>
  <div class="page-list">
    <!-- 查询条件 -->
    <el-form :inline="true" :model="params" class="demo-form-inline" size="small">
      <el-form-item label="站点">
        <el-select v-model="params.siteId" placeholder="请选择站点">
          <el-option v-for="(site, index) in siteList" :key="index" :label="site.siteName" :value="site.siteId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="页面别名">
        <el-input v-model="params.pageAliase" placeholder="请输入页面别名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.prevent="handleQuery" icon="el-icon-search">查询</el-button>
      </el-form-item>
      <!-- 新增页面按钮 -->
      <el-button @click.prevent="handleAdd" type="primary"  size="small" icon="el-icon-plus">新增页面</el-button>
    </el-form>

    <!-- 页面列表 -->
    <el-table :data="list" style="width: 100%">
      <el-table-column type="selection" width="55"/>
      <el-table-column type="index" label="序号" width="50"/>
      <el-table-column prop="pageName" label="页面名称" width="180"/>
      <el-table-column prop="pageAliase" label="别名" width="120"/>
      <el-table-column prop="pageType" label="页面类型" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.pageType === '0' ? '静态' : '动态' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="pageWebPath" label="访问路径"/>
      <el-table-column prop="pagePhysicalPath" label="物理路径"/>
      <el-table-column prop="pageCreateTime" label="创建时间" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.pageCreateTime | date-format}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页工具栏 -->
    <el-pagination @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage"
                   :page-sizes="[5, 10, 15, 30, 40]"
                   :page-size="size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total" style="margin-top: 15px">
    </el-pagination>

    <!-- 新增页面 -->
    <add-modal ref="addModel" ></add-modal>
  </div>
</template>

<script>
  import * as CmsPageAPI from '../../../api/cms/page/index'
  import addModal from './pageModel.vue'
  export default {
    data() {
      return {
        siteList: [], // 站点列表
        list: [],
        total: 0,
        currentPage: 1,
        page: 1,//页码
        size: 5,//每页显示个数
        params: {
          siteId: '', // 站点ID
          pageAliase: '',// 页面别名
        },

        isShowAddModal: false, // 新增页面Drawer显示状态
      }
    },
    mounted () {
      // 默认查询页面
      this.handleQuery()
      //初始化站点列表
      this.siteList = [
          {
            siteId:'5a751fab6abb5044e0d19ea1',
            siteName:'门户主站'
          },
          {
            siteId:'102',
            siteName:'测试站'
          }
      ]
    },
    methods: {

      // 查询
      async handleQuery () {
        const result = await CmsPageAPI.getPageList(this.page, this.size, this.params)
        const {queryResult} = result
        this.total = queryResult.total
        this.list = queryResult.list
      },
      handleAdd () {
        this.$refs.addModel.title = '新增'
        this.$refs.addModel.handleAdd()
      },
      handleEdit () {
        this.$refs.addModel.title = '编辑'
        this.$refs.addModel.handleAdd()
      },
      handleDelete () {

      },
      handleSizeChange(size) {
        console.log(`每页 ${size} 条`);
        this._changePage(this.page, size)
      },
      handleCurrentChange(page) {
        console.log(`当前页: ${page}`);
        this._changePage(page, this.size)
      },
      // 分页查询
      _changePage (page, size) {
        this.page = page
        this.size = size
        this.handleQuery()
      },
    },
    components: {
      addModal
    }
  }
</script>
