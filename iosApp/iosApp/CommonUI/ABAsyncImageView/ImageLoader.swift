//
//  ImageLoader.swift
//  iosApp
//
//  Created by Sergey Prybysh on 1/12/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Combine
import Foundation
import UIKit

enum AsyncImagePhase : Sendable {
  case empty
  case success(Image, originalImage: UIImage)
  case failure(any Error)
}

class ImageLoader: ObservableObject {
  
  private let url: URL?
  
  init(url: URL?) {
    self.url = url
  }
  deinit {
    cancel()
  }
  
  private static let session: URLSession = {
    let configuration = URLSessionConfiguration.default
    configuration.requestCachePolicy = .returnCacheDataElseLoad
    configuration.timeoutIntervalForRequest = 12
    let session = URLSession(configuration: configuration)
    return session
  }()
  
  private enum LoaderError: Error {
    case missingURL
    case failedToDecodeFromData
  }
  
  @Published var phase = AsyncImagePhase.empty
  private var subscriptions: [AnyCancellable] = []
  
  func load() {
    guard let url else {
      phase = .failure(LoaderError.missingURL)
      return
    }
    
    ImageLoader.session.dataTaskPublisher(for: url)
      .receive(on: DispatchQueue.main)
      .sink(receiveCompletion: { completion in
        switch completion {
        case .finished:
          break
        case .failure(let error):
          self.phase = .failure(error)
        }
      }, receiveValue: {
        if let image = UIImage(data: $0.data) {
          self.phase = .success(Image(uiImage: image), originalImage: image)
        } else {
          self.phase = .failure(LoaderError.failedToDecodeFromData)
        }
      })
      .store(in: &subscriptions)
  }
  
  func cancel() {
    subscriptions.forEach { $0.cancel() }
  }
}
