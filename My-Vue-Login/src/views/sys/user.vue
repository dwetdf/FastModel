<script setup>

// eslint-disable-next-line no-unused-vars
import { rules } from 'eslint-plugin-vue'
</script>

<template>
  <div>
    <!-- 搜索栏 -->
    <el-card v-if="this.$store.state.globalVariable.isAdmin" id="search1" >

      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.username" placeholder="用户名" clearable />
          <el-input v-model="searchModel.phone" placeholder="电话" clearable />
          <el-button type="primary" round icon="el-icon-search" @click="getUserList">查询</el-button>
        </el-col>

        <el-col :span="4" align="right">
          <el-button type="primary" icon="el-icon-plus" circle @click="openEditUI" />
        </el-col>
      </el-row>

    </el-card>

    <!-- 结果列表 -->
    <el-card v-if="this.$store.state.globalVariable.isAdmin">
      <el-table :data="userList" stripe style="width: 100%">
        <el-table-column label="#" width="80">
          <template slot-scope="scope">
            <!-- (pageNo-1) * pageSize + index + 1 -->
            {{ (searchModel.pageNo-1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="id" label="用户ID" width="180" />
        <el-table-column prop="username" label="用户名" width="180" />
        <el-table-column prop="phone" label="电话" width="180" />
        <el-table-column prop="email" label="电子邮件" />
        <el-table-column prop="status" label="用户状态" width="180"/>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle size="mini" @click="openEditUI(scope.row.id)" />
            <el-button v-if="scope.row.id!='1'" type="danger" icon="el-icon-delete" circle size="mini" @click="deleteUser(scope.row)" />
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-row v-if="!this.$store.state.globalVariable.isAdmin">
      <h1>当前用户不具有管理员权限</h1>
    </el-row>

    <!-- 分页插件 -->
    <el-pagination
      v-if="this.$store.state.globalVariable.isAdmin"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 30, 40]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!-- 用户信息编辑对话框 -->
    <!-- :title 加了冒号就变成一个变量，属性绑定了,后面的东西需要在data中说明 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clearForm">
      <el-form ref="userFormRef" :model="userForm" :rules="rules">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-model="userForm.username" autocomplete="off" />
        </el-form-item>

        <el-form-item
          v-if="userForm.id == null || userForm.id == undefined"
          label="登录密码"
          prop="password"
          :label-width="formLabelWidth"
        >
          <el-input v-model="userForm.password" type="password" autocomplete="off" />
        </el-form-item>

        <el-form-item label="联系电话" prop="phone" :label-width="formLabelWidth">
          <el-input v-model="userForm.phone" autocomplete="off" />
        </el-form-item>

        <el-form-item label="用户状态" prop="status" :label-width="formLabelWidth">
          <el-switch
            v-model="userForm.status"
            active-value="正常"
            inactive-value="账户异常"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />
        </el-form-item>

        <el-form-item label="电子邮件" prop="email" :label-width="formLabelWidth">
          <el-input v-model="userForm.email" autocomplete="off" />
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<!-- 定义查询对象 -->
<script>
// 前后端对接的配置
import userApi from '@/api/userManage'

// 页面变量
export default {
  data() {
    const checkEmail = (rule, value, callback) => {
      const reg = /^([a-zA-Z\d][\w-]{2,})@(\w{2,})\.([a-z]{2,})(\.[a-z]{2,})?$/
      if (!reg.test(value)) {
        return callback(new Error('邮箱格式错误'))
      }
      callback() // 效验成功
    }

    const checkPhone = (rule, value, callback) => {
      const reg = /^[1]+\d{10}$/
      if (!reg.test(value)) {
        return callback(new Error('请输入正确的手机号码'))
      }
      callback() // 效验成功
    }

    return {
      formLabelWidth: '130px',
      userForm: {},
      dialogFormVisible: false,
      title: '',
      total: 0,
      searchModel: { // 当前页面参数，会调用方法传给后端
        pageNo: 1,
        pageSize: 5
      },
      userList: [],
      rules: { // 表单校验
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入初始密码', trigger: 'blur' },
          { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入电子邮件', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ]
      }

    }
  },

  // 钩子函数定义处
  created() {
    this.getUserList()
  },

  methods: {
    clearForm() {
      // 清空表单
      this.userForm = {}
    },

    openEditUI(id) {
      if (id == null) {
        this.title = '新增用户'
        this.dialogFormVisible = true
      } else {
        this.title = '修改用户'
        // 根据用户查询id
        userApi.getUserById(id).then(response => {
          this.userForm = response.data
        })
      }
      this.dialogFormVisible = true
    },

    // 此处对应pageSize和pageNo改变时更改前端的显示
    handleSizeChange(pageSize) {
      // 更新数据，完毕后重新调用函数
      this.searchModel.pageSize = pageSize
      this.getUserList()
    },

    handleCurrentChange(pageNo) {
      // 更新数据，完毕后重新调用函数
      this.searchModel.pageNo = pageNo
      this.getUserList()
    },
    getUserList() {
      // userApi.getUserList 开始调用后端，并传递参数searchModel
      // then表示后端查询后返回的数据
      userApi.getUserList(this.searchModel).then(response => {
        this.userList = response.data.rows // 后端返回的 json中的字段
        this.total = response.data.total
      })
    },
    saveUser() {
      // 触发表单验证
      this.$refs.userFormRef.validate((valid) => {
        if (valid) { // 验证通过
          // 数据传给后端
          userApi.saveUser(this.userForm).then(response => {
            // 提交成功后的操作

            // 插入数据成功提示
            this.$message({
              message: response.message,
              type: 'success'
            })

            // 关闭对话框，清除表单数据
            this.dialogFormVisible = false
            this.clearForm()

            // 刷新表格
            this.getUserList()
          })
        } else { // 验证失败
          console.log('error submit!!')
          return false
        }
      })
    },

    deleteUser(user) {
      this.$confirm(`您确认删除用户${user.username}吗?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userApi.deleteUserById(user.id).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.getUserList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
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

<style scoped lang="scss">

</style>
