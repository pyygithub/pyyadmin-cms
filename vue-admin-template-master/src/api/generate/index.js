import { createAPI } from '@/utils/request'

export const testConnection = (params) => createAPI('/generate/testConnection', 'post', params)

export const getGenerateModel = (params) => createAPI('/generate/getGenerateModel', 'post', params)

export const getTables = (params) => createAPI('/generate/getTables', 'post', params)

export const generateModels = (params) => createAPI('/generate/generateModels', 'post', params)

