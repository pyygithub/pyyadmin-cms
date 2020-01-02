import { createAPI } from '@/utils/request'

export const testConnection = (params) => createAPI('/v1/testConnection', 'post', params)

export const getGenerateModel = (params) => createAPI('/v1/getGenerateModel', 'post', params)

export const getTables = (params) => createAPI('/v1/getTables', 'post', params)

export const generateModels = (params) => createAPI('/v1/generateModels', 'post', params)

