<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="busName">
      <el-input v-model="dataForm.busName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="procedure">
      <el-input v-model="dataForm.procedure" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="requirement">
      <el-input v-model="dataForm.requirement" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="cost">
      <el-input v-model="dataForm.cost" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          busId: 0,
          busName: '',
          procedure: '',
          requirement: '',
          cost: ''
        },
        dataRule: {
          busName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          procedure: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          requirement: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          cost: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.busId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.busId) {
            this.$http({
              url: this.$http.adornUrl(`/goverment/business/info/${this.dataForm.busId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.busName = data.business.busName
                this.dataForm.procedure = data.business.procedure
                this.dataForm.requirement = data.business.requirement
                this.dataForm.cost = data.business.cost
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/goverment/business/${!this.dataForm.busId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'busId': this.dataForm.busId || undefined,
                'busName': this.dataForm.busName,
                'procedure': this.dataForm.procedure,
                'requirement': this.dataForm.requirement,
                'cost': this.dataForm.cost
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
