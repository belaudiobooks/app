//
//  RepositoryClient.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/29/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import Foundation
import Shared

class RepositoryClient {
  let repository: Repository
  
  init() {
    repository = getRepositoryClient()
  }
}
