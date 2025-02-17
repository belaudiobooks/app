import SwiftUI

@main
struct iOSApp: App {
  @ObservedObject private var router: Router
  private let services: Services
  private let homeComponent: HomeComponent
  private let repositoryClient: RepositoryClient
      
  init() {
    let router = Router(selectedTab: 0)
    self.router = router
    self.repositoryClient = RepositoryClient()
    let colorProvider = ColorProvider()
    services = Services(router: router, repositoryClient: repositoryClient, colorProvider: colorProvider)
    homeComponent = HomeComponent(arguments: HomeArguments(), services: services)
  }
  
  var body: some Scene {
    WindowGroup {
      TabView(selection: $router.selectedTab) {
        NavigationStack(path: $router.homePath) {
          homeComponent.view
        }
          .tabItem {
            Image(systemName: "house")
            Text("Home")
          }
        
        Text("Catalog")
          .tabItem {
            Image(systemName: "books.vertical")
            Text("Catalog")
          }
        
        Text("Search")
          .tabItem {
            Image(systemName: "magnifyingglass")
            Text("Search")
          }
      }
    }
  }
}
