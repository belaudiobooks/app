//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Sergey Prybysh on 5/29/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
// Import of the shared library is in ViewModel leaving UI logic independent 
import Shared

// Example of the basic MVVM architecture. We will add more complexity as we go and if we needed.

class ContentViewModel: ObservableObject {
    enum Action {
        case didTapShowContent
    }
    
    @Published var state: ViewState<ContentView.Success, ContentView.Error> = .loading
    
    private let greeting = Greeting().greet()
    
    // Provides easier access to the copy of the successful state
    private var successfulState: ContentView.Success? {
        guard case let .success(success) = state else { return nil }
        return success
    }
    
    init(state: ViewState<ContentView.Success, ContentView.Error>) {
        self.state = state
    }
    
    func handle(action: Action) {
        switch action {
        case .didTapShowContent:
            if var successfulState {
              successfulState.showContent.toggle()
                state = .success(successfulState)
            } else {
                state = .success(.init(showContent: true, greeting: greeting))
            }
        }
    }
}
