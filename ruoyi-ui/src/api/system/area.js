import request from '@/utils/request'

// 查询区域管理列表
export function listArea(query) {
  return request({
    url: '/system/area/list',
    method: 'get',
    params: query
  })
}

// 查询区域管理详细
export function getArea(areaId) {
  return request({
    url: '/system/area/' + areaId,
    method: 'get'
  })
}

// 新增区域管理
export function addArea(data) {
  return request({
    url: '/system/area',
    method: 'post',
    data: data
  })
}

// 修改区域管理
export function updateArea(data) {
  return request({
    url: '/system/area',
    method: 'put',
    data: data
  })
}

// 删除区域管理
export function delArea(areaId) {
  return request({
    url: '/system/area/' + areaId,
    method: 'delete'
  })
}
