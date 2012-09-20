load miniserver.groovy

s = serv(9090, {
  req -> [[], 'hello', 200]
})
s.start()
