<template>
  <div>
    <el-card id="search4">
      <el-row>
        <el-col :span="12">
          <el-button type="primary" round @click="fetchmodulepowersupply()">一键侦测</el-button>
        </el-col>
      </el-row>
    </el-card>
    <el-table
      :data="ElectricityData"
      height="600"
      border
      style="width: 100%">
      <el-table-column
        prop="id"
        label="模块ID"
        width="160"
        align="center">
      </el-table-column>
      <el-table-column
        prop="inU"
        label="输入电压/V"
        width="160"
        align="center">
      </el-table-column>
      <el-table-column
        prop="outU"
        label="输出电压/V"
        width="160"
        align="center">
      </el-table-column>
      <el-table-column
        prop="I"
        label="电流/A"
        width="160"
        align="center">
      </el-table-column>
      <el-table-column
        prop="P"
        label="功率/W"
        width="160"
        align="center">
      </el-table-column>
      <el-table-column
        prop="state"
        label="供电状态"
        width="240"
        align="center">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import display1Api from '@/api/resourceDisplay'

export default {
  data() {
    return {
      ElectricityData: []
    }
  },
  mounted() {
    this.fetchmodulepowersupply()
  },
  methods: {
    async fetchmodulepowersupply() {
      display1Api.getModule().then(response => {
        this.ElectricityData = response.data
      })
      this.$message({ message: '已进行侦测', type: 'success', center: true })
    }
  }
}
</script>

<style scoped lang="scss">

</style>
<style scoped>
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

.box-card1 {
  width: 300px;
}
</style>
