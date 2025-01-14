//
//  ABAsyncImageView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 1/12/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Combine
import Foundation
import SwiftUI

struct ABAsyncImageView<Content, L, E>: View where Content: View, L: View, E: View {
  @StateObject fileprivate var loader: ImageLoader
  @ViewBuilder private var content: (Image) -> Content
  @ViewBuilder private var loading: () -> L
  @ViewBuilder private var error: () -> E
  
  init(
    url: URL?,
    @ViewBuilder content: @escaping (Image) -> Content,
    @ViewBuilder loading: @escaping () -> L,
    @ViewBuilder error: @escaping () -> E) {
    _loader = .init(wrappedValue: ImageLoader(url: url))
      self.content = content
      self.loading = loading
      self.error = error
  }
  
  var body: some View {
    Group {
      switch loader.phase {
      case .empty:
        loading()
      case .success(let image):
        content(image)
      case .failure:
        error()
      @unknown default:
        error()
      }
    }
    .onAppear {
      loader.load()
    }
  }
}
