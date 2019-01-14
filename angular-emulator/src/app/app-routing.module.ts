import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoresComponent } from './cores/cores.component';
import { CoreDetailComponent } from './core-detail/core-detail.component';
import { CoreAddComponent} from './core-add/core-add.component';
import { CoreEditComponent} from './core-edit/core-edit.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KeysComponent } from './keys/keys.component'


const routes: Routes = [
  {
    path: 'cores',
    component: CoresComponent,
    data: { title: 'List of Cores' }
  },
  {
    path: 'core-details/:id',
    component: CoreDetailComponent,
    data: { title: 'Core Details' }
  },
  {
    path: 'core-add',
    component: CoreAddComponent,
    data: { title: 'Add Core' }
  },
  {
    path: 'core-edit/:id',
    component: CoreEditComponent,
    data: { title: 'Edit Core' }
  },
  { path: '',
    redirectTo: '/cores',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {
}
