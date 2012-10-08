load MiniServer.groovy

MiniServer.start {
  req -> [[], "hello ${req.params.name}!", 200]
}
