import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useStore = defineStore('store', () => {
  const menus = [
    {
      mid: 1,
      name: "dashboards",
      title: '数据统计',
      icon: ['/src/assets/icons/dashboards.png', '/src/assets/icons/dashboards-s.png'],
      url: "/dashboards",
      children: []
    },
    {
      mid: 2,
      name: "inout",
      title: '出入库管理',
      icon: ['/src/assets/icons/inout.png', '/src/assets/icons/inout-s.png'],
      url: "/inout",
      children: [
        {
          mid: 2.1,
          name: "in",
          title: "入库管理",
          icon: ['/src/assets/icons/children.png', '/src/assets/icons/children-s.png'],
          url: "/inout/in",
          children: []
        },
        {
          mid: 2.2,
          name: "out",
          title: "出库管理",
          icon: ['/src/assets/icons/children.png', '/src/assets/icons/children-s.png'],
          url: "/inout/out",
          children: []
        }
      ]
    },
    {
      mid: 3,
      name: "repertory",
      title: '库存管理',
      icon: ['/src/assets/icons/repertory.png', '/src/assets/icons/repertory-s.png'],
      url: "/repertory",
      children: [
        {
          mid: 3.1,
          name: "product",
          title: "产品管理",
          icon: ['/src/assets/icons/children.png', '/src/assets/icons/children-s.png'],
          url: "/repertory/product",
          children: []
        },
        {
          mid: 3.2,
          name: "inventory",
          title: "库存管理",
          icon: ['/src/assets/icons/children.png', '/src/assets/icons/children-s.png'],
          url: "/repertory/inventory",
          children: []
        },
        {
          mid: 3.3,
          name: "warehouse",
          title: "仓库管理",
          icon: ['/src/assets/icons/children.png', '/src/assets/icons/children-s.png'],
          url: "/repertory/warehouse",
          children: []
        }
      ]
    },
    {
      mid: 4,
      name: "users",
      title: '员工管理',
      icon: ['/src/assets/icons/users.png', '/src/assets/icons/users-s.png'],
      url: "/users",
      children: []
    },
    // {
    //   mid: 5,
    //   name: "orders",
    //   title: '订单管理',
    //   icon: ['/src/assets/icons/order.png', '/src/assets/icons/order-s.png'],
    //   url: "/orders",
    //   children: []
    // },
  ]

  function getMenu(rule) {
    // 请求接口，获取权限列表，模拟[1,2,3,5]
    let ruleArr = []
    if (rule == 'admin') {
      ruleArr = [1, 2, 3, 4, 5]
    }
    if (rule == 'user') {
      ruleArr = [1, 2, 3, 5]
    }
    const result = []
    ruleArr.forEach(item => {
      menus.forEach(menu => {
        if (menu.mid === item) {
          result.push(menu)
        }
      })
    })
    return result
  }


  const user = ref(null)
  const setUser = (data) => {
    user.value = data
  }
  const getUser = computed(() => user.value)
  return { menus, getMenu, setUser, getUser }
})
