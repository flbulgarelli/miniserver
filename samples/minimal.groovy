load MiniServer.groovy

MiniServer.start {
  req -> [[], 'hello', 200]
}
