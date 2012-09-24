import org.mortbay.jetty.*
import org.mortbay.jetty.servlet.*
import groovy.servlet.*
import javax.servlet.http.*
import javax.servlet.*

@Grab('org.mortbay.jetty:jetty-embedded:6.1.14')
def serve(react) {
 new Server(9090).with {
   new Context(it, '/').with {
     addServlet(new ServletHolder(new HttpServlet() {
       void service(ServletRequest req, ServletResponse res) {
          def (headers, body, status) = react(req)
          res.outputStream << body
          res.status = status
          headers.each { k, v ->  
           res.addHeader(k, v)
          }
       }
     }), '/')
     resourceBase = '.'
   }     
   it
 }
}

HttpServletRequest.metaClass {
  getParams = { -> delegate.parameterMap.collectEntries { k, v -> [k, v[0]] } }
}
