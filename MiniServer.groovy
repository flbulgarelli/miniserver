import org.mortbay.jetty.*
import org.mortbay.jetty.servlet.*
import groovy.servlet.*
import javax.servlet.http.*
import javax.servlet.*

@Grab('org.mortbay.jetty:jetty-embedded:6.1.14')
class MiniServer {

  static {
    HttpServletRequest.metaClass {
      getParams = { -> delegate.parameterMap.collectEntries { k, v -> [k, v[0]] } }
    }
  }

  private final jetty
 
  MiniServer() {
    this(9090)   
  }

  MiniServer(port) {
    this.jetty = new Server(port)
  }

  def start() {
     Thread.start {    
        jetty.start()
     }
  }

  def stop() {
    jetty.stop()
  }

  def serve(path, react) {
    new Context(jetty, path).with {
     addServlet(new ServletHolder(new HttpServlet() {
       void service(ServletRequest req, ServletResponse res) {
          def (headers, body, status) = react(req)
          res.outputStream << body
          res.status = status
          headers.each { k, v ->  
           res.addHeader(k, v)
          }
       }
     }), path)
     resourceBase = '.'
   }
  }

  static start(react) {
    start('/': react)
  }

  static start(Map routes) {
    new MiniServer().with {
       routes.each { path, react ->
         serve(path, react) 
       }
       start()
       it
    }
  }
  
}


