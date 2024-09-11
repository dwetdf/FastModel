<template>
  <div>
    <el-card id="select1">
      <el-row>
        <el-col :span="6">
          <h3>请选择图像处理算法</h3>
          <el-radio v-model="id1" label="1">Gauss</el-radio>
          <el-radio v-model="id1" label="2">Sobel</el-radio>
        </el-col>
        <el-col :span="13" align="right">
          <el-select v-model="value" placeholder="模式" style="width: 200px;">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3" align="right">
          <el-button type="primary" round @click="imagetest()">自适应测试</el-button>
        </el-col>
        <el-col :span="1" align="right" style="display: flex; justify-content: center; align-items: center;">
          <el-tag v-if="id1 === '1'" style="height: 40px; display: flex; justify-content: center; align-items: center; transition: none;">模块:{{ ida }}</el-tag>
          <el-tag v-if="id1 === '2'" style="height: 40px; display: flex; justify-content: center; align-items: center; transition: none;">模块:{{ idb }}</el-tag>
        </el-col>
      </el-row>
      <el-divider />
      <el-row>
        <el-col :span="4" align="left">
          <input v-show="id1 === '1'" type="file" accept=".jpg,.png,.jpeg" @change="handleFileChange1">
        </el-col>
        <el-col :span="4" align="left">
          <input v-show="id1 === '2'" type="file" accept=".jpg,.png,.jpeg" @change="handleFileChange2">
        </el-col>

        <el-col :span="20" align="right">
          <el-input v-model="id" placeholder="模块ID" clearable @input="handleInput" />
          <el-button type="primary" round icon="el-icon-s-promotion" @click="imagetestById()">发送测试</el-button>
        </el-col>
      </el-row>
      <el-divider />
      <div class="demo-image">
        <div v-for="fit in fits" :key="fit" class="block">
          <el-image
            style="width: 540px; height: 340px"
            :src="id1 === '1' ? url1 : url2"
            :fit="fit"
          >
            <div slot="error" class="image-slot">
              <i>输入图片</i>
            </div>
          </el-image>
          <el-image
            style="width: 540px; height: 340px"
            :src=" id1 === '1'? output1:output2 "
            :fit="fit"
          >
            <div slot="error" class="image-slot">
              <i>输出图片</i>
            </div>
          </el-image>
        </div>
      </div>
    </el-card>

  </div>
</template>

<script>
import imagetest from '@/api/imagetest'

export default {

  data() {
    return {
      id: null,
      id1: '1',
      ida: null,
      idb: null,
      selectedFile1: null,
      selectedFile2: null,
      output1: null,
      output2: null,
      url1: null,
      url2: null,
      options: [{
        value: '1',
        label: 'CPU最优'
      }, {
        value: '2',
        label: '内存最优'
      }, {
        value: '3',
        label: '网络最优'
      }],
      value: '1',
      fits: ['fill']
    }
  },
  methods: {
    handleInput() {
      const value = parseInt(this.id)
      if (isNaN(value)) {
        // 如果不是整数，则将输入的值设置为null
        this.id = null
      } else {
        // 如果是整数，则更新输入的值为整数
        this.id = value
      }
    },
    handleFileChange1(event) {
      const file = event.target.files[0]
      if (!file) {
        this.selectedFile1 = null
        this.url1 = null
        return // 用户取消选择文件时，file置为null，直接返回
      }
      // 检查文件类型
      if (!file.name.endsWith('.jpg') &&
        !file.name.endsWith('.png') &&
        !file.name.endsWith('.jpeg') &&
        !file.name.endsWith('.JPG') &&
        !file.name.endsWith('.PNG') &&
        !file.name.endsWith('.JPEG')) {
        this.$message({ message: '请选择一个图像文件', type: 'error', center: true })
        // 清空文件输入框
        event.target.value = ''
        this.selectedFile1 = null
        this.url1 = null
        return
      }
      // 可以在这里做其他文件校验或处理
      this.selectedFile1 = file
      this.url1 = URL.createObjectURL(this.selectedFile1)
    },

    handleFileChange2(event) {
      const file = event.target.files[0]
      if (!file) {
        this.selectedFile2 = null
        this.url2 = null
        return // 用户取消选择文件时，file置为null，直接返回
      }
      // 检查文件类型
      if (!file.name.endsWith('.jpg') &&
        !file.name.endsWith('.png') &&
        !file.name.endsWith('.jpeg') &&
        !file.name.endsWith('.JPG') &&
        !file.name.endsWith('.PNG') &&
        !file.name.endsWith('.JPEG')) {
        this.$message({ message: '请选择一个图像文件', type: 'error', center: true })
        // 清空文件输入框
        event.target.value = ''
        this.selectedFile2 = null
        this.url2 = null
        return
      }
      // 可以在这里做其他文件校验或处理
      this.selectedFile2 = file
      this.url2 = URL.createObjectURL(this.selectedFile2)
    },
    async imagetestById() {
      if (this.id === null) {
        this.$message({ message: '模块id不能为空', type: 'error', center: true })
        this.output1 = null
        this.ida = null
        this.output2 = null
        this.idb = null
        return 0
      }
      if (this.id1 === '1') {
        if (this.selectedFile1 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output1 = null
          this.ida = null
          return null
        }
        const { data } = await imagetest.GaussById(this.id, this.selectedFile1)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.output1 = data
        this.ida = null
      }
      if (this.id1 === '2') {
        if (this.selectedFile2 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output2 = null
          this.idb = null
          return null
        }
        const { data } = await imagetest.BYJCById(this.id, this.selectedFile2)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.output2 = data
        this.idb = null
      }
    },
    async imagetest() {
      if (this.id1 === '1') {
        if (this.selectedFile1 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output1 = null
          this.ida = null
          return null
        }
        const { data } = await imagetest.Gauss(this.value, this.selectedFile1)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.output1 = data.file
        this.ida = data.id
      }
      if (this.id1 === '2') {
        if (this.selectedFile2 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output2 = null
          this.idb = null
          return null
        }
        const { data } = await imagetest.BYJC(this.value, this.selectedFile2)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.output2 = data.file
        this.idb = data.id
      }
    }
  }
}
</script>

<style scoped>
#select1 .el-input {
  width: 100px;
  margin-right: 10px;
}
.el-dialog .el-input{
  width: 85%;
}
.el-button {
  width: 120px;
  margin-right: 10px;
}
</style>

<style scoped lang="scss">

</style>
