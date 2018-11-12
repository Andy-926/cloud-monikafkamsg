# 配置说明
  - 现有数据库：dzh、gongshang、lawsuit、patent、prismcq、qmp、prismzz、search、stock、tm
  - 数据源配置：datasource.${database}.${fields}
  - mapper路径：mybatis.${database}.mapper-locations: classpath:mapper/${database}/*.xml
  

  - 现有集群：monitor、search、search-special
  - 集群配置：jest.${cluster}.${fields}
  - 可用JestClient对象：monitorJestClient、jestClient、searchSpecialJestClient
  
  - jindi.code.[tmClass、tmStatus、tmCat]（商标 Map<Integer, String>）
  
  - oss: aliyun.oss.[accessKeyId、accessKeySecret、endpoint]

