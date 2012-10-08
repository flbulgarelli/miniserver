load MiniServer.groovy

MiniServer.start(
  '/':     { req -> [[], 'hello', 200] },
  '/foo':  { req -> [[], 'foo', 200] },
  '/bar':  { req -> [[], 'bar', 200] }
)
