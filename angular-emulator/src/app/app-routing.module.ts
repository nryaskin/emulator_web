import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoresComponent } from './cores/cores.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CoreDetailComponent } from './core-detail/core-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'cores', component: CoresComponent },
  { path: 'detail/:id', component: CoreDetailComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {
}
