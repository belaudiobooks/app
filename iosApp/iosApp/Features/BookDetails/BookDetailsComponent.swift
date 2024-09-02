//
//  BookDetailsComponent.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct BookDetailsFeature {
  @ObservableState
  struct State: Equatable {
    let book: BookCover
  }
  
  enum Action {
    
  }
  
  var body: some ReducerOf<Self> {
    Reduce { state, action in
      switch action {
        
      }
    }
  }
}
