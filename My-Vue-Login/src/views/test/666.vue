<template>
  <div>
    <el-card id="search4">
      <el-row>
        <el-col :span="12">
          <el-button type="primary" round @click="fetchHardwareStatus()">一键获取</el-button>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card">
      <div v-for="o in 1" :key="o" class="text item">
        CPU利用率: {{ cpuUsage }}%
      </div>
      <el-divider />
      <div v-for="o in 1" :key="o" class="text item">
        总物理内存: {{ totalMem }}MB
      </div>
      <el-divider />
      <div v-for="o in 1" :key="o" class="text item">
        剩余可用内存: {{ lastMem }}MB
      </div>
      <el-divider />
      <div v-for="o in 1" :key="o" class="text item">
        内存利用率: {{ memUsage }}%
      </div>
    </el-card>
  </div>
</template>

<script>
import display1Api from '@/api/resourceDisplay'

export default {
  data() {
    return {
      cpuUsage: null,
      memUsage: null,
      totalMem: null,
      lastMem: null
    }
  },
  mounted() {
    this.fetchHardwareStatus()
  },
  methods: {
    async fetchHardwareStatus() {
      this.$message({ message: '已进行获取', type: 'success', center: true })
      try {
        const { data } = await display1Api.getCpuUsage() // 使用解构直接获取响应对象中的data属性
        this.cpuUsage = data // 将data赋值给cpuUsage
      } catch (error) {
        console.error('Error fetching cpuUsage:', error)
      }
      try {
        const { data } = await display1Api.getMemUsage()
        this.memUsage = data
      } catch (error) {
        console.error('Error fetching memUsage:', error)
      }
      try {
        const { data } = await display1Api.getTotalMem()
        this.totalMem = data
      } catch (error) {
        console.error('Error fetching totalMem:', error)
      }
      try {
        const { data } = await display1Api.getLastMem()
        this.lastMem = data
      } catch (error) {
        console.error('Error fetching lastMem:', error)
      }
    }
  }
}
</script>

<style scoped lang="scss">

</style>

<style>
#search4 .el-button {
  width: 100px;
  margin-right: 10px;
}
</style>

<style>
.text {
  font-size: 24px;
  font-family: Hiragino Sans GB,Helvetica Neue;
  font-palette: Blod;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 400px;
}
</style>
