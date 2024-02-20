import { createRouter, createWebHistory } from 'vue-router'
import { useStore } from "../stores/store"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/dashboards",
      component: () => import("../layout/Layout.vue"),
      children: [
        {
          path: "/dashboards",
          name: "dashboards",
          component: () => import("../views/Dashboards.vue")
        },
        {
          path: "/inout",
          name: "inout",
          component: () => import("../views/Inout.vue"),
          redirect: "/inout/in",
          children: [
            {
              path: "/inout/in",
              name: "in",
              component: () => import("../views/Inout/In.vue")
            },
            {
              path: "/inout/out",
              name: "out",
              component: () => import("../views/Inout/Out.vue")
            }
          ]
        },
        {
          path: "/repertory",
          name: "repertory",
          component: () => import("../views/Repertory.vue"),
          redirect: "/repertory/product",
          children: [
            {
              path: "/repertory/product",
              name: "product",
              component: () => import("../views/Repertory/Product.vue")
            },
            {
              path: "/repertory/inventory",
              name: "inventory",
              component: () => import("../views/Repertory/Inventory.vue")
            },
            {
              path: "/repertory/warehouse",
              name: "warehouse",
              component: () => import("../views/Repertory/Warehouse.vue")
            }
          ]
        },
        {
          path: "/users",
          name: "users",
          component: () => import("../views/Users.vue")
        },
        {
          path: "/orders",
          name: "orders",
          component: () => import("../views/Orders.vue")
        }
      ]
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/Login.vue")
    }
  ]
})

// 路由拦截器
router.beforeEach((to, from, next) => {
  const store = useStore()
  if (from.path === "/login") {
    next()
  }

  if (!store.getUser) {
    if (to.path !== "/login") {
      next({ path: "/login" })
    } else {
      // 从localStorage中获取用户信息
      const account = JSON.parse(localStorage.getItem("account"))
      if (account) {
        store.setUser(account)
        next({ path: "/" })
      }
      next()
    }
  } else {
    next()
  }
  console.log("路由拦截器：", "to:", to, "from:", from);
})
export default router
