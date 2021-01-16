<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="busId">
      <el-input v-model="dataForm.busId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="description">
      <el-input v-model="dataForm.description" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="photoUrl">
      <el-input v-model="dataForm.photoUrl" placeholder=""></el-input>
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
          materialId: 0,
          busId: '',
          description: '',
          photoUrl: ''
        },
        dataRule: {
          busId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          photoUrl: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.materialId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.materialId) {
            this.$http({
              url: this.$http.adornUrl(`/goverment/material/info/${this.dataForm.materialId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.busId = data.material.busId
                this.dataForm.description = data.material.description
                this.dataForm.photoUrl = data.material.photoUrl
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
              url: this.$http.adornUrl(`/goverment/material/${!this.dataForm.materialId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'materialId': this.dataForm.materialId || undefined,
                'busId': this.dataForm.busId,
                'description': this.dataForm.description,
                'photoUrl': this.dataForm.photoUrl
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
