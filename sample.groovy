load miniserver.groovy

s = serve({
  req -> [[], 'hello', 200]
})
s.start()
