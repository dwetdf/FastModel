import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.Length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will become nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will not redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '资源中心', icon: 'dashboard' }
    }]
  },
  {
    path: '/display',
    component: Layout,
    name: 'display',
    redirect: '/display/display1',
    meta: { title: '资源监控', icon: 'form' },
    children: [
      {
        path: 'display2',
        component: () => import('@/views/display/display2'),
        name: 'display2',
        meta: { title: '资源运行侦测', icon: 'el-icon-s-help' }
      },
      {
        path: 'display3',
        component: () => import('@/views/display/display3'),
        name: 'display3',
        meta: { title: '供电侦测', icon: 'el-icon-s-help' }
      },
      {
        path: 'display4',
        component: () => import('@/views/display/display4'),
        name: 'display4',
        meta: { title: '处理单元性能', icon: 'el-icon-s-help' }
      }
    ]

  },

  {
    path: '/test',
    component: Layout,
    redirect: '/test/test1',
    name: 'test',
    meta: { title: '测试中心', icon: 'form' },
    // 这个是二级路由
    children: [
      {
        path: 'test1',
        name: 'test1',
        component: () => import('@/views/test/test1'),
        meta: { title: '信号处理测试', icon: 'el-icon-s-help' }
      },
      {
        path: 'test2',
        name: 'test2',
        component: () => import('@/views/test/test2'),
        meta: { title: '图像测试', icon: 'el-icon-s-help' }
      },
      {
        path: 'test3',
        name: 'test3',
        component: () => import('@/views/test/test3'),
        meta: { title: 'AI识别测试', icon: 'el-icon-s-help' }
      },
      {
        path: 'test4',
        name: 'test4',
        component: () => import('@/views/test/test4'),
        meta: { title: '连接测试', icon: 'el-icon-s-help' }
      }
    ]
  },

  {
    path: '/testlib',
    component: Layout,
    redirect: '/testlib/signal',
    name: 'testlib',
    meta: { title: '测试库', icon: 'form' },
    children: [
      {
        path: 'signal',
        name: 'signal',
        component: () => import('@/views/testlib/signal'),
        meta: { title: '信号处理算法库', icon: 'el-icon-s-help' }
      },
      {
        path: 'pic',
        name: 'pic',
        component: () => import('@/views/testlib/pic'),
        meta: { title: '图像处理算法库', icon: 'el-icon-s-help' }
      },
      {
        path: 'AI',
        name: 'AI',
        component: () => import('@/views/testlib/AI'),
        meta: { title: 'AI识别算法库', icon: 'el-icon-s-help' }
      }
    ]
  },

  {
    path: '/sys/user',
    component: Layout,
    redirect: '/sys/user',
    name: 'sysManage',
    meta: { title: '系统管理', icon: 'example' },
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/sys/user'),
        meta: { title: '用户管理', icon: 'user' }
      },
      {
        path: 'role',
        name: 'role',
        component: () => import('@/views/sys/role'),
        meta: { title: '保留功能', icon: 'user' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
