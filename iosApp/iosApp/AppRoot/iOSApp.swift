import SwiftUI

@main
struct iOSApp: App {
  let homeComponent = HomeComponent(arguments: HomeArguments(), delegate: nil)
  
  var body: some Scene {
    WindowGroup {
      TabView {
        homeComponent.view
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
