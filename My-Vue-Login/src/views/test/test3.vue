<template>
  <div>
    <el-card id="select1">
      <el-row>
        <el-col :span="6">
          <h3>请选择AI识别算法</h3>
          <el-radio v-model="id1" label="1">Yolo</el-radio>
          <el-radio v-model="id1" label="2">Select</el-radio>
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
          <el-button type="primary" round @click="AItest()">自适应测试</el-button>
        </el-col>
        <el-col :span="1" align="right" style="display: flex; justify-content: center; align-items: center;">
          <el-tag v-if="id1 === '1'" style="height: 40px; display: flex; justify-content: center; align-items: center; transition: none;">模块:{{ ida }}</el-tag>
          <el-tag v-if="id1 === '2'" style="height: 40px; display: flex; justify-content: center; align-items: center; transition: none;">模块:{{ idb }}</el-tag>
        </el-col>
      </el-row>
      <el-divider />
      <el-row>
        <el-col :span="4" align="left">
          <input v-show="id1 === '1'" id="9" type="file" accept=".avi,.mp4,.mov,.wmv" @change="handleFileChange1"/>
        </el-col>
        <el-col :span="4" align="left">
          <input v-show="id1 === '2'" type="file" accept=".jpg,.png,.jpeg" @change="handleFileChange2" />
        </el-col>
        <el-col :span="4" align="right">
          <input v-show="id1 === '2'" type="file" accept=".jpg,.png,.jpeg" @change="handleFileChange3" />
        </el-col>

        <el-col :span="16" align="right">
          <el-input v-model="id" placeholder="模块ID" clearable @input="handleInput" />
          <el-button type="primary" round icon="el-icon-s-promotion" @click="AItestById()">发送测试</el-button>
        </el-col>
      </el-row>

      <el-divider />
      <div class="video-containers">
        <div v-if="id1 === '1'" class="video-container">
          <video v-if="url1" :key="url1" controls class="video-player">
            <source :src="url1" type="video/mp4">
          </video>
        </div>
        <div v-if="id1 === '1'" class="video-container">
          <video v-if="output1" :key="output1" controls class="video-player">
            <source :src="video" type="video/mp4">
          </video>
        </div>
      </div>
      <div class="demo-image">
        <div v-for="fit in fits" :key="fit" class="block">
          <el-image
            v-show="id1 === '2'"
            style="width: 340px; height: 340px"
            :src="url2"
            :fit="fit"
          >
            <div slot="error" class="image-slot">
              <i>输入图片1</i>
            </div>
          </el-image>
          <el-image
            v-show="id1 === '2'"
            style="width: 340px; height: 340px"
            :src="url3"
            :fit="fit"
          >
            <div slot="error" class="image-slot">
              <i>输入图片2</i>
            </div>
          </el-image>
          <el-image
            v-show="id1 === '2'"
            style="width: 540px; height: 340px"
            :src="output2"
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
import AItest from '@/api/AItest'

export default {
  data() {
    return {
      id: null,
      id1: '1',
      ida: null,
      idb: null,
      selectedFile1: null,
      path: null,
      selectedFile2: null,
      selectedFile3: null,
      output1: null,
      output2: null,
      video: null,
      url1: null,
      url2: null,
      url3: null,
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
  mounted() {
    this.decodeBase64Video()
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
      if (!file.name.endsWith('.avi') &&
        !file.name.endsWith('.mp4') &&
        !file.name.endsWith('.mov') &&
        !file.name.endsWith('.wmv')) {
        this.$message({ message: '请选择一个视频文件', type: 'error', center: true })
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
    handleFileChange3(event) {
      const file = event.target.files[0]
      if (!file) {
        this.selectedFile3 = null
        this.url3 = null
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
        this.selectedFile3 = null
        this.url3 = null
        return
      }
      // 可以在这里做其他文件校验或处理
      this.selectedFile3 = file
      this.url3 = URL.createObjectURL(this.selectedFile3)
    },
    async AItestById() {
      if (this.id === null) {
        this.$message({ message: '模块id不能为空', type: 'error', center: true })
        this.output1 = null
        this.video = null
        this.ida = null
        this.output2 = null
        this.idb = null
        return 0
      }
      if (this.id1 === '1') {
        if (this.selectedFile1 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output1 = null
          this.video = null
          this.ida = null
          return null
        }
        const { data } = await AItest.YoloById(this.id, this.selectedFile1)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
          this.output1 = data
          await this.decodeBase64Video()
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.ida = null
      }
      if (this.id1 === '2') {
        if (this.selectedFile2 === null || this.selectedFile3 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output2 = null
          this.idb = null
          return null
        }
        const { data } = await AItest.SelectById(this.id, this.selectedFile2, this.selectedFile3)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.output2 = data
        this.idb = null
      }
    },
    async AItest() {
      if (this.id1 === '1') {
        if (this.selectedFile1 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output1 = null
          this.video = null
          this.ida = null
          return null
        }
        const { data } = await AItest.Yolo(this.value, this.selectedFile1)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
          this.output1 = data.file
          await this.decodeBase64Video()
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.ida = data.id
      }
      if (this.id1 === '2') {
        if (this.selectedFile2 === null || this.selectedFile3 === null) {
          this.$message({ message: '文件不能为空', type: 'error', center: true })
          this.output2 = null
          this.idb = null
          return null
        }
        const { data } = await AItest.Select(this.value, this.selectedFile2, this.selectedFile3)
        if (data !== null) {
          this.$message({ message: '处理成功', type: 'success', center: true })
        } else this.$message({ message: '请检查模块ID或网络连接', type: 'warning', center: true })
        this.output2 = data.file
        this.idb = data.id
      }
    },
    async decodeBase64Video() {
      // 使用base64-js库解码Base64字符串
      const decodedData = atob(this.output1)
      const arrayBuffer = new ArrayBuffer(decodedData.length)
      const uint8Array = new Uint8Array(arrayBuffer)
      for (let i = 0; i < decodedData.length; i++) {
        uint8Array[i] = decodedData.charCodeAt(i)
      }
      const blob = new Blob([arrayBuffer], { type: 'video/mp4' })
      this.video = URL.createObjectURL(blob)
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

<style scoped>
.video-container {
  width: 600px; /* 设置容器的最大宽度 */
  height: 400px; /* 根据视频比例自动计算高度 */
}
.video-player {
  width: 100%; /* 设置视频宽度为容器宽度 */
  height: 100%; /* 设置视频高度为容器高度 */
  object-fit: fill; /* 设置视频填充选项为 cover，保持视频比例并填充整个容器 */
}
.video-containers {
  display: flex; /* 使用 flex 布局 */
  justify-content: space-between; /* 水平方向上均匀分布 */
}
</style>

<style scoped lang="scss">

</style>
