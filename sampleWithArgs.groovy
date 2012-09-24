load miniserver.groovy

s = serve({
  req -> [[], "hello ${req.params.name}!", 200]
})
s.start()
