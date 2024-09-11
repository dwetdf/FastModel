<template>
  <div>
    <el-card class="search3">
      <el-row>
        <el-col :span="5" align="right">
          <el-button type="primary" round @click="Heartbeat()">心跳连接测试</el-button>
        </el-col>
        <el-col :span="8" align="right">
          <el-button type="primary" round @click="TaskComm()">任务通信测试</el-button>
        </el-col>
        <el-col :span="8" align="right">
          <el-button type="primary" round @click="WebLink()">网络连接测试</el-button>
        </el-col>
      </el-row>
    </el-card>
    <el-row style="display: flex; flex-wrap: nowrap;">
      <el-table
        :data="StateData"
        height="500"
        border
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="模块ID"
          width="180"
          align="center"
        />
        <el-table-column
          prop="state"
          label="Agent心跳连接状态"
          width="240"
          align="center"
        />
      </el-table>
      <el-table
        :data="TaskData"
        height="500"
        border
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="模块ID"
          width="180"
          align="center"
        />
        <el-table-column
          prop="task"
          label="任务通信状态"
          width="240"
          align="center"
        />
      </el-table>
      <el-table
        :data="LinkData"
        height="500"
        border
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="模块ID"
          width="105"
          align="center"
        />
        <el-table-column
          prop="link"
          label="网络连接状态"
          width="105"
          align="center"
        />
        <el-table-column
          prop="bandwidth"
          label="网络带宽"
          width="105"
          align="center"
        />
        <el-table-column
          prop="delay"
          label="响应延迟"
          width="105"
          align="center"
        />
      </el-table>
    </el-row>
  </div>
</template>

<script>
import linktestApi from '@/api/linktestconfig'

export default {
  data() {
    return {
      StateData: [],
      TaskData: [],
      LinkData: []
    }
  },
  mounted() {
    this.Heartbeat()
    this.TaskComm()
    this.WebLink()
  },
  methods: {
    async Heartbeat() {
      try {
        linktestApi.Heartbeat().then(response => {
          this.StateData = response.data
        })
        this.$message({ message: '已进行测试', type: 'success', center: true })
      } catch (error) {
        console.error('Error fetching heartbeat:', error)
        this.state = '心跳停止'
      }
    },
    async TaskComm() {
      try {
        linktestApi.TaskComm().then(response => {
          this.TaskData = response.data
        })
        this.$message({ message: '已进行测试', type: 'success', center: true })
      } catch (error) {
        console.error('Error fetching taskcomm:', error)
        this.task = '通信中断'
      }
    },
    async WebLink() {
      try {
        linktestApi.WebLink().then(response => {
          this.LinkData = response.data
        })
        this.$message({ message: '已进行测试', type: 'success', center: true })
      } catch (error) {
        console.error('Error fetching weblink:', error)
        this.link = '断开连接'
      }
    }
  }
}
</script>
<style scoped>
#search3 .el-button {
  width: 150px;
  margin-right: 10px;
}
.el-input {
   width: 100px;
   margin-right: 10px;
 }
.el-dialog .el-input {
  width: 85%;
}
</style>

<style scoped lang="scss">

</style>
