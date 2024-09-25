//
//  Router.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import SwiftUI

//class Router: ObservableObject {
//  
//  public enum Destination: Codable, Hashable {
//    case livingroom
//    case bedroom(owner: String)
//  }
//  
//  @Published var homePath = NavigationPath()
//  
//  func navigate(to destination: Destination) {
//    homePath.append(destination)
//  }
//  
//  func navigateBack() {
//    homePath.removeLast()
//  }
//  
//  func navigateToRoot() {
//    homePath.removeLast(homePath.count)
//  }
//}

final class Router: ObservableObject {
    @Published var selectedTab: Int
    @Published var homePath: NavigationPath
    @Published var catalogPath: NavigationPath
    @Published var searchPath: NavigationPath
    
//    private lazy var decoder = JSONDecoder()
//    private lazy var encoder = JSONEncoder()

    init(
      selectedTab: Int,
      homePath: NavigationPath = NavigationPath()
    ) {
        self.selectedTab = selectedTab
        self.homePath = homePath
        self.catalogPath = homePath
        self.searchPath = homePath
    }

//    var selectedBook: String? {
//        get { bookPath.first }
//        set { bookPath = [newValue].compactMap { $0 } }
//    }

//    var jsonData: Data? {
//        get { try? encoder.encode(self) }
//        set {
//            guard 
//              let data = newValue,
//              let model = try? decoder.decode(Self.self, from: data)
//            else { return }
//          selectedTab = model.selectedTab
//          navigationPath = model.navigationPath
//        }
//    }

    var objectWillChangeSequence: AsyncPublisher<Publishers.Buffer<ObservableObjectPublisher>> {
        objectWillChange
            .buffer(size: 1, prefetch: .byRequest, whenFull: .dropOldest)
            .values
    }

//    required init(from decoder: Decoder) throws {
//        let container = try decoder.container(keyedBy: CodingKeys.self)
//        self.selectedTab = try container.decode(
//            Int.self, forKey: .selectedTab)
//        if let codableNavigationPath = try container.decodeIfPresent(
//          NavigationPath.CodableRepresentation.self, forKey: .navigationPath) {
//          navigationPath = NavigationPath(codableNavigationPath)
//        } else {
//          navigationPath = NavigationPath()
//        }
//    }

//    func encode(to encoder: Encoder) throws {
//        var container = encoder.container(keyedBy: CodingKeys.self)
//        try container.encode(selectedTab, forKey: .selectedTab)
//        try container.encode(navigationPath.codable, forKey: .navigationPath)
//    }
//
//    enum CodingKeys: String, CodingKey {
//        case selectedTab
//        case navigationPath
//    }
}

