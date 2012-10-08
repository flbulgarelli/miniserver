load MiniServer.groovy

new MiniServer(8080).with {
  serveAt('/')    { req -> [[], 'hello', 200] }
  serveAt('/foo') { req -> [[], 'foo', 200] }
  serveAt('/bar') { req -> [[], 'bar', 200] }
  start()
}
