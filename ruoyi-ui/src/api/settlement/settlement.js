import request from '@/utils/request'

// 查询结算管理列表
export function listSettlement(query) {
  return request({
    url: '/settlement/settlement/list',
    method: 'get',
    params: query
  })
}

// 查询结算管理详细
export function getSettlement(settlementId) {
  return request({
    url: '/settlement/settlement/' + settlementId,
    method: 'get'
  })
}

// 新增结算管理
export function addSettlement(data) {
  return request({
    url: '/settlement/settlement',
    method: 'post',
    data: data
  })
}

// 修改结算管理
export function updateSettlement(data) {
  return request({
    url: '/settlement/settlement',
    method: 'put',
    data: data
  })
}

// 删除结算管理
export function delSettlement(settlementId) {
  return request({
    url: '/settlement/settlement/' + settlementId,
    method: 'delete'
  })
}
