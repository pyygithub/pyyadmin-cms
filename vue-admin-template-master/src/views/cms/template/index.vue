<template>
  <div class="template-list">
    <!-- 查询条件 -->
    <el-form :inline="true" :model="params" class="demo-form-inline" size="small">
      <el-form-item label="页面模板名称">
        <el-input v-model="params.templateName" placeholder="请输入模板名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.prevent="handleQuery" icon="el-icon-search">查询</el-button>
      </el-form-item>
      <!-- 新增页面模板按钮 -->
      <el-button @click.prevent="handleAdd" type="primary"  size="small" icon="el-icon-plus">新增页面模板</el-button>
    </el-form>

    <!-- 页面模板列表 -->
    <el-table :data="list" style="width: 100%">
      <el-table-column type="index" label="序号" width="50"/>
      <el-table-column prop="templateName" label="模版名称" />
      <el-table-column prop="templateParameter" label="模版参数"/>
      <el-table-column prop="templateFileId" label="模版文件ID"/>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.row.templateId)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.templateId)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页工具栏 -->
    <el-pagination @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage"
                   :template-sizes="[5, 10, 15, 30, 40]"
                   :page-size="size"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="total" style="margin-top: 15px">
    </el-pagination>

    <!-- 新增页面模板 -->
    <add-modal ref="addModel" ></add-modal>
  </div>
</template>

<script>
  import * as cmsTemplateAPI from '../../../api/cms/template/index'
  import addModal from './templateModel.vue'
  export default {
    data() {
      return {
        list: [], // 页面模板列表
        total: 0,
        currentPage: 1,
        page: 1,//页码
        size: 5,//每页显示个数
        params: {
          templateName: '',// 页面模板名称
        },

        isShowAddModal: false, // 新增页面模板Drawer显示状态
      }
    },
    mounted () {
      // 默认查询页面模板
      this.handleQuery()
    },
    methods: {
      // 查询
      async handleQuery () {
        const result = await cmsTemplateAPI.getTemplatePageList(this.page, this.size, this.params)
        const queryResult = result.data
        this.total = queryResult.total
        this.list = queryResult.list
      },
      handleAdd () {
        this.$refs.addModel.title = '新增'
        this.$refs.addModel.openAdd()
      },
      handleEdit (templateId) {
        this.$refs.addModel.title = '编辑'
        this.$refs.addModel.openEdit(templateId)
      },
      handleDelete (templateId) {
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          type: 'warning'
        }).then(async () => {
          // 执行异步删除
          await cmsTemplateAPI.deleteTemplate(templateId)
          this.$message({type: 'success', message: '删除成功!'});
          // 刷新列表
          this.handleQuery()

        }).catch(() => {
          this.$message({type: 'info', message: '已取消删除'});
        });
      },
      handleSizeChange(size) {
        console.log(`每页 ${size} 条`);
        this._changePage(this.template, size)
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
