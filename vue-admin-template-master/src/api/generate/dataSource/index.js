import { createAPI } from '@/utils/request'

export const getDataSourceById = (id) => createAPI(`/generate/dataSource/${id}`, 'get')

export const getDataSourceList = (params) => createAPI('/generate/dataSources', 'get', params)

export const getDataSourcePageList = (page, size, params) => createAPI(`/generate/dataSources/${page}/${size}`, 'get', params)

export const addDataSource = (dataSource) => createAPI('/generate/dataSource', 'post', dataSource)

export const updateDataSource = (id, dataSource) => createAPI(`/generate/dataSource/${id}`, 'put', dataSource)

export const deleteDataSource = (id) => createAPI(`/generate/dataSource/${id}`, 'delete')



