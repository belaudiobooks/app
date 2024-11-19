//
//  Router.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import SwiftUI

final class Router: ObservableObject {
  @Published var selectedTab: Int
  @Published var homePath: NavigationPath
  @Published var catalogPath: NavigationPath
  @Published var searchPath: NavigationPath
  
  init(
    selectedTab: Int,
    homePath: NavigationPath = NavigationPath(),
    catalogPath: NavigationPath = NavigationPath(),
    searchPath: NavigationPath = NavigationPath()) {
    self.selectedTab = selectedTab
    self.homePath = homePath
    self.catalogPath = catalogPath
    self.searchPath = searchPath
  }
  
  var objectWillChangeSequence: AsyncPublisher<Publishers.Buffer<ObservableObjectPublisher>> {
    objectWillChange
      .buffer(size: 1, prefetch: .byRequest, whenFull: .dropOldest)
      .values
  }
}

