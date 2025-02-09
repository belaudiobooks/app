//
//  Services.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

class Services {
  let router: Router
  let repositoryClient: RepositoryClient
  let colorProvider: ColorProvider
  
  init(router: Router, repositoryClient: RepositoryClient, colorProvider: ColorProvider) {
    self.router = router
    self.repositoryClient = repositoryClient
    self.colorProvider = colorProvider 
  }
}
