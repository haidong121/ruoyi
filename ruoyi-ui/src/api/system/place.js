import request from '@/utils/request'

// 查询车位管理列表
export function listPlace(query) {
  return request({
    url: '/system/place/list',
    method: 'get',
    params: query
  })
}

// 查询车位管理详细
export function getPlace(placeId) {
  return request({
    url: '/system/place/' + placeId,
    method: 'get'
  })
}

// 新增车位管理
export function addPlace(data) {
  return request({
    url: '/system/place',
    method: 'post',
    data: data
  })
}

// 修改车位管理
export function updatePlace(data) {
  return request({
    url: '/system/place',
    method: 'put',
    data: data
  })
}

// 删除车位管理
export function delPlace(placeId) {
  return request({
    url: '/system/place/' + placeId,
    method: 'delete'
  })
}
