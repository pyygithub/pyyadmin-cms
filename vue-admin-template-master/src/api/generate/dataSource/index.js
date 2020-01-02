import { createAPI } from '@/utils/request'

export const getDataSourceById = (id) => createAPI(`/v1/dataSource/${id}`, 'get')

export const getDataSourceList = (params) => createAPI('/v1/dataSources', 'get', params)

export const getDataSourcePageList = (page, size, params) => createAPI(`/v1/dataSources/${page}/${size}`, 'get', params)

export const addDataSource = (dataSource) => createAPI('/v1/dataSource', 'post', dataSource)

export const updateDataSource = (id, dataSource) => createAPI(`/v1/dataSource/${id}`, 'put', dataSource)

export const deleteDataSource = (id) => createAPI(`/v1/dataSource/${id}`, 'delete')



