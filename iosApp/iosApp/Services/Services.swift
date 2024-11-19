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
  
  init(router: Router, repositoryClient: RepositoryClient) {
    self.router = router
    self.repositoryClient = repositoryClient
  }
}
