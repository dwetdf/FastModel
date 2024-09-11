<template>
  <div>
    <el-card id="search1">

      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.shortname" placeholder="算法简称" clearable />
          <el-input v-model="searchModel.user" placeholder="加入用户" clearable />
          <el-button type="primary" round icon="el-icon-search" @click="getaiList">查询</el-button>
        </el-col>
      </el-row>

    </el-card>
    <el-card>
      <el-table :data="aiList" stripe style="width: 100%">
        <el-table-column label="#" width="80">
          <template slot-scope="scope">
            <!-- (pageNo-1) * pageSize + index + 1 -->
            {{ (searchModel.pageNo-1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="id" label="算法ID" />
        <el-table-column prop="name" label="算法名" width="180" />
        <el-table-column prop="shortname" label="算法简称" />
        <el-table-column prop="user" label="加入用户" width="120"/>
        <el-table-column prop="date" label="加入时间" width="240"/>
        <el-table-column prop="status" label="算法状态" width="180"/>
        <el-table-column prop="url" label="前往测试" width="180">
          <template slot-scope="scope">
            <a :href="scope.row.url">点击前往</a>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-pagination
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 30, 40]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import libApi from '@/api/lib'

export default {
  data() {
    return {
      searchModel: { // 当前页面参数，会调用方法传给后端
        pageNo: 1,
        pageSize: 5
      },
      total: 0,
      aiList: []
    }
  },
  created() {
    this.getaiList()
  },
  methods: {
    handleSizeChange(pageSize) {
      // 更新数据，完毕后重新调用函数
      this.searchModel.pageSize = pageSize
      this.getaiList()
    },
    handleCurrentChange(pageNo) {
      // 更新数据，完毕后重新调用函数
      this.searchModel.pageNo = pageNo
      this.getaiList()
    },
    getaiList() {
      // then表示后端查询后返回的数据
      libApi.getaiList(this.searchModel).then(response => {
        this.aiList = response.data.rows // 后端返回的 json中的字段
        this.total = response.data.total
      })
    }
  }
}

</script>

<style scoped>
#search1 .el-input {
  width: 200px;
  margin-right: 10px;
}
.el-dialog .el-input{
  width: 85%;
}
</style>
