import request from '@/utils/request'

// 查询城区管理列表
export function listCity(query) {
  return request({
    url: '/system/city/list',
    method: 'get',
    params: query
  })
}

// 查询城区管理详细
export function getCity(cityId) {
  return request({
    url: '/system/city/' + cityId,
    method: 'get'
  })
}

// 新增城区管理
export function addCity(data) {
  return request({
    url: '/system/city',
    method: 'post',
    data: data
  })
}

// 修改城区管理
export function updateCity(data) {
  return request({
    url: '/system/city',
    method: 'put',
    data: data
  })
}

// 删除城区管理
export function delCity(cityId) {
  return request({
    url: '/system/city/' + cityId,
    method: 'delete'
  })
}

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/system/city/treeselect',
    method: 'get'
  })
}

