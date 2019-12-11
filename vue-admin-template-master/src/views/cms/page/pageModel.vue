<template>
  <el-drawer
    :title="title"
    :visible.sync="visible"
    :direction="direction">
    <div class="model-content">
      <el-form :model="pageForm" label-width="80px" size="small">
        <el-form-item label="所属站点" prop="siteId">
          <el-select v-model="pageForm.siteId" placeholder="请选择站点">
            <el-option v-for="item in siteList" :key="item.siteId" :label="item.siteName"
                       :value="item.siteId"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="选择模版" prop="templateId">
          <el-select v-model="pageForm.templateId" placeholder="请选择">
            <el-option v-for="item in templateList" :key="item.templateId" :label="item.templateName"
                       :value="item.templateId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="页面名称" prop="pageName">
          <el-input v-model="pageForm.pageName" auto-complete="off" autofocus></el-input>
        </el-form-item>
        <el-form-item label="别名" prop="pageAliase">
          <el-input v-model="pageForm.pageAliase" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="访问路径" prop="pageWebPath">
          <el-input v-model="pageForm.pageWebPath" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="物理路径" prop="pagePhysicalPath">
          <el-input v-model="pageForm.pagePhysicalPath" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-radio-group v-model="pageForm.pageType">
            <el-radio class="radio" label="0">静态</el-radio>
            <el-radio class="radio" label="1">动态</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker type="datetime" placeholder="创建时间"
                          v-model="pageForm.pageCreateTime"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: right">
        <el-button type="plain" size="small" @click="addSubmit">关闭</el-button>
        <el-button type="primary" size="small" @click="addSubmit">提交</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<script type="text/ecmascript-6">
  export default {
    name: 'addModal',
    data () {
      return {
        title: '',
        visible: false,
        confirmLoading: false,
        //站点列表
        siteList:[],
        //模版列表
        templateList:[],
        //新增界面数据
        pageForm: {
            siteId:'',
          templateId:'',
          pageName: '',
          pageAliase: '',
          pageWebPath: '',
          pageParameter:'',
          pagePhysicalPath:'',
          pageType:'',
          pageCreateTime: new Date()
        }
      }
    },
    methods: {
      handleAdd () {
        this.visible = true
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss">
  .model-content {
    margin: 24px;
    padding: 15px;
    border: 1px solid rgb(233, 233, 233);
  }
  .el-drawer__header {
    padding: 16px 24px;
    border-radius: 4px 4px 0 0;
    background: #fff;
    color: rgba(0,0,0,.65);
    border-bottom: 1px solid #e8e8e8;
    margin-bottom: 0px;
  }
</style>
